package de.tum.in.ase.eist;

import de.tum.in.ase.eist.grpc.*;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WeatherReporterServer {
    private static final Logger logger = Logger.getLogger(WeatherReporterServer.class.getName());

    private final int port;
    private final Server server;

    /**
     * Create a WeatherReporterServer server listening on {@code port} using {@code cityWeatherData}.
     */
    public WeatherReporterServer(int port, ArrayList<CityWeatherData> cityWeatherData) throws IOException {
        this(Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()),
                port, cityWeatherData);
    }

    /**
     * Create a WeatherReporterServer server using serverBuilder as a base
     */
    public WeatherReporterServer(ServerBuilder<?> serverBuilder, int port, ArrayList<CityWeatherData> cityWeatherData) {
        this.port = port;
        server = serverBuilder.addService(new WeatherReporterService(cityWeatherData)).build();
    }

    /**
     * Start serving requests.
     */
    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    WeatherReporterServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    /**
     * Stop serving requests and shutdown resources.
     */
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<CityWeatherData> cityWeatherData = new ArrayList<>();
        WeatherReporterServer server = new WeatherReporterServer(8980, cityWeatherData);
        server.start();
        server.blockUntilShutdown();
    }

// build task of Grandle only create abstract class -> conrete impl. for the rpc methode in .proto needed //
    private static class WeatherReporterService extends WeatherReporterGrpc.WeatherReporterImplBase {
        private final ArrayList<CityWeatherData> allWeatherData;

        private WeatherReporterService(ArrayList<CityWeatherData> allWeatherData) {
            this.allWeatherData = allWeatherData;
        }

        // TODO: Task 2: Write an Implementation for the rpc methods
        @Override
        public void getCityWeatherSingleDay(LocationDate request, StreamObserver<CityWeatherData> responseObserver) {
            CityWeatherData cityWeatherData = null;

            for (int i = 0; i < allWeatherData.size(); i++) {
                cityWeatherData = allWeatherData.get(i);
                if (cityWeatherData.getLocationDate().equals(request)) {
                    break;
                }
            }

            if (cityWeatherData == null) {
                return;
            }

            responseObserver.onNext(cityWeatherData);
            responseObserver.onCompleted();
        }

        public void getCityWeatherMultipleDays(LocationDatePeriod request,
                                           StreamObserver<CityWeatherData> responseObserver) {
            for (CityWeatherData singleCityWeatherData : allWeatherData) {
                if (singleCityWeatherData.getLocationDate().getLocation().equals(request.getLocation())
                        && isWithinDatePeriod(singleCityWeatherData.getLocationDate().getDate(), request)) {
                    responseObserver.onNext(singleCityWeatherData);
                }
            }
            responseObserver.onCompleted();
        }

        private boolean isWithinDatePeriod(Date date, LocationDatePeriod locationDatePeriod) {
            boolean isAfterStartDate = compareDates(date, locationDatePeriod.getStartDate());
            boolean isBeforeEndDate = compareDates(locationDatePeriod.getEndDate(), date);
            return isAfterStartDate && isBeforeEndDate;
        }

        private boolean compareDates(Date date1, Date date2) {
            if (date1.equals(date2)) {
                return true;
            } else if (date1.getYear() > date2.getYear()) {
                    return true;
            } else if (date1.getYear() == date2.getYear()) {
                if (date1.getMonth() > date2.getMonth()) {
                    return true;
                } else if (date1.getMonth() == date2.getMonth()) {
                    return date1.getDay() > date2.getDay();
                }
            }
            return false;
        }
    }

}

    