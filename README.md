# Quarkus trivial app to reproduce issue on Semeru 17 FIPS mode

requires a Semeru 17 JVM running on a RedHat x86_64 system with FIPS mode enabled.  See https://www.ibm.com/support/pages/node/6612693 and https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/8/html/security_hardening/using-the-system-wide-cryptographic-policies_security-hardening#switching-the-system-to-fips-mode_using-the-system-wide-cryptographic-policies

Download Semeru and install Semeru from https://github.com/ibmruntimes/semeru17-certified-binaries/releases/download/jdk-17.0.8.1%2B1_openj9-0.40.0/ibm-semeru-certified-jdk_x64_linux_17.0.8.1.tar.gz


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
