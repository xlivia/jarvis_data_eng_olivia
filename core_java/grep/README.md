# Core Java Grep
## Introduction
The Core Java Grep application is a command-line tool that allows users to search for specific patterns in text files using regular expressions.
The application is built using core Java, leveraging Java I/O and regular expression libraries.
It can be run on any Java-supported environment, such as Eclipse or IntelliJ IDEA, without the need for any additional frameworks or dependencies.

## Quick Start
To use the Core Java Grep application, follow these steps:
1. Compile the Java source files using `javac GrepApp.java`.
2. Run the application using `java GrepApp <pattern> <file>`, where `<pattern>` represents the regular expression pattern to search for, and `<file>` is the path to the file to search.

For example, to search for the word "hello" in a file named "sample.txt", you would run `java GrepApp hello sample.txt`.

## Implemenation
### Pseudocode
The `process` method, responsible for searching the file for matching lines, can be outlined with the following pseudocode:
```
function process(pattern, filePath):
    open file with filePath
    for each line in file:
        if pattern matches line:
            print line
        close file
```
This pseudocode represents the basic logic of iterating through each line of the file, checking if it matches the given pattern, and printing the matching lines.

### Performance Issue
One potential performance issue in this application is the memory consumption when processing large files.
Reading the entire file into memory might not be efficient or feasible in such cases.

To address this issue, a possible solution is to process the file line by line instead of loading it entirely into memory.
By using the `BufferedReader` class, we can read and process the file incrementally, reducing the memory footprint.
This approach allows the application to handle large files efficiently.

## Test
During the development and testing of the Core Java Grep application, the following manual testing approach was followed:
1. Prepare sample data by creating test files with different patterns and matching lines.
2. Run the application with various patterns and files.
3. Manually inspect the output to ensure the correct lines are being matched and displayed.
4. Verify the application's behavior with edge cases, such as empty files or files with no matching lines.
This manual testing approach helps verify the functionality and correctness of the application.

## Deployment
To make the Core Java Grep application easier to distribute, you can dockerize it. Here's a general outline of the steps involved:
1. Create a Dockerfile in the project directory.
2. Configure the Dockerfile with the necessary instructions to build the application image, including installing Java and copying the application files.
3. Build the Docker image using the Dockerfile.
4. Run the Docker container using the created image, specifying the necessary command-line arguments.
Dockerizing the application simplifies its deployment by packaging all the dependencies and configurations into a self-contained container.

## Improvement
Three potential improvements for this project could be:
1. Adding support for multiple file searches: Enhancing the application to accept multiple file paths or even directories as input, allowing users to search across multiple files simultaneously.
2. Implementing multithreading: Introducing multithreading capabilities to improve the application's performance when searching large files or multiple files concurrently. This would enable parallel processing of file lines, speeding up the search process.
3. Providing more advanced search options: Expanding the application to support additional search options, such as case sensitivity or search filters based on file extensions. This would enhance the flexibility and usability of the tool.
