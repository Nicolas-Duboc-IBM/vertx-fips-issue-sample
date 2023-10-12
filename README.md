# Quarkus trivial app to reproduce issue on Semeru 17 FIPS mode

Compile with
```
mvnw clean package
```

Configure your PATH to point to a Semeru 17 on a RedHat x86_64 machine in FIPS mode:

```shell
$ java -version 
java version "17.0.8.1" 2023-08-24
IBM Semeru Runtime Certified Edition 17.0.8.1 (build 17.0.8.1+1)
Eclipse OpenJ9 VM 17.0.8.1 (build openj9-0.40.0, JRE 17 Linux amd64-64-Bit Compressed References 20230824_485 (JIT enabled, AOT enabled)
OpenJ9   - d12d10c9e
OMR      - e80bff83b
JCL      - 87c2977201e based on jdk-17.0.8.1+1)

$ ./run.sh
```
