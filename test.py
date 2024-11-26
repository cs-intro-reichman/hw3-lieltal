import json
import subprocess

# Load test configurations from a JSON file
def load_tests(filename):
    with open(filename, 'r') as file:
        data = json.load(file)
    return data['tests']

PASSED = True

# Run a single test
def run_test(test):
    print("test")
    global PASSED
    print(f"Running: {test['name']}")
    try:
        # Compile the Java program
        subprocess.run(test['setup'], check=True, shell=True)
        
        # Run the compiled Java program
        result = subprocess.run(test['run'], check=True, shell=True, text=True, capture_output=True)
        # Compare the output
        actual_output = result.stdout.strip()
        if actual_output != test['output']:
            print("Test Failed")
            print(f"Expected: {test['output']}")
            print(f"Got: {actual_output}")
            PASSED = False

    except subprocess.CalledProcessError as e:
        print(f"An error occurred while running the test: {e}")

# Main function to load tests and run them
def main():
    tests = load_tests('.github/classroom/autograding.json')
    for test in tests:
        name = test['name'].split(" ")
        if name[0] == "Basic":
            run_test(test)

    if not PASSED:
        print("FAILED!!!!")
if __name__ == "__main__":
    main()