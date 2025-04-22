package de.tum.in.ase.eist.eventmicroservice;

import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/event/")
public class EventController {

    ArrayList<Employee> registeredList = new ArrayList<>();
    ArrayList<Event> preferencesList = new ArrayList<>();

    //TODO 2: Finish the implementation of the registerEvent() endpoint
    @PostMapping("registerEvent")
    public String registerEvent(@RequestBody Employee employee) {
        String token = tokenHash(employee.getName() + employee.getId());

        if (employee.getToken().equals(token)) {
            //TODO: Check the token of the employee

            //TODO: Add the employee to the registered list
            registeredList.add(employee);
            return "Registration is successful";
        } else {
            //TODO: print "Registration failed"
            return "Registration failed";
        }
    }

    @PostMapping("recordPreferences")
    public String recordPreferences(@RequestBody Employee employee) {
        if (employee.getEventPreferences() != null) {
            preferencesList.add(employee.getEventPreferences());
            return "Event preferences recorded successfully";
        } else {
            return "Event preferences aren't set";
        }
    }

    public static String tokenHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            String saltedInput = input + "EIST2023";

            byte[] hashedBytes = digest.digest(saltedInput.getBytes(StandardCharsets.UTF_8));

            StringBuilder stringBuilder = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                stringBuilder.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/registeredList")
    public ArrayList<Employee> getRegisteredList() {
        return registeredList;
    }

    @GetMapping("/preferencesList")
    public ArrayList<Event> getPreferencesList() {
        return preferencesList;
    }
}
