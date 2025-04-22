import os
import subprocess

def git_configured():
    try:
        subprocess.check_output(["git", "config", "--get", "user.email"])
        subprocess.check_output(["git", "config", "--get", "user.name"])
        return True
    except subprocess.CalledProcessError:
        return False

def branch_exists(branch_name):
    try:
        subprocess.check_output(["git", "rev-parse", "--verify", branch_name])
        return True
    except subprocess.CalledProcessError:
        return False

patch_directory = "patches"
patch_files = os.listdir(patch_directory)

if not git_configured():
    print("Git user name and email are not set. Please configure Git using the following commands:")
    print("git config --global user.name \"Your Name\"")
    print("git config --global user.email \"Your Email\"")
    exit(1)

print("Stashing local changes (if any)")
os.system("git stash")

for patch_file in patch_files:
    branch_name = "feature/" + os.path.splitext(patch_file)[0]

    if branch_exists(branch_name):
        print(f"Branch {branch_name} already exists. Skipping.")
        continue

    print(f"Checking out: {branch_name}")
    os.system(f"git checkout -b {branch_name}")

    patch_path = os.path.join(patch_directory, patch_file)
    print(f"Applying {patch_path}")
    os.system(f"git apply {patch_path}")

    print("Staging the changes.")
    os.system("git add .")
    print(f"Committing to: {branch_name}")
    os.system(f'git commit -m "Apply patch {patch_path}" --author="SOMEONE ELSE <other.developer@tum.de>"')

    os.system("git checkout main")

patch_path = os.path.join("final-patch", "Add-initial-false-implementation.patch")
os.system(f"git apply {patch_path}")
os.system("git add .")
os.system(f'git commit -m "Apply patch {patch_path}"')
print("Here's a list of your branches. Make sure you see the feature branches. They are relevant for this exercise.")
os.system("git branch")

