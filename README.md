# Quarkus trivial app to reproduce issue on Semeru 17 FIPS mode

requires a Semeru 17 JVM running on a RedHat x86_64 system with FIPS mode enabled.  See https://www.ibm.com/support/pages/node/6612693 and https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/8/html/security_hardening/using-the-system-wide-cryptographic-policies_security-hardening#switching-the-system-to-fips-mode_using-the-system-wide-cryptographic-policies

Download Semeru and install Semeru from https://github.com/ibmruntimes/semeru17-certified-binaries/releases/download/jdk-17.0.8.1%2B1_openj9-0.40.0/ibm-semeru-certified-jdk_x64_linux_17.0.8.1.tar.gz


Compile with (may require compilation with a non FIPS JDK)
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
```

Run:

```
$ ./run.sh
+ java -Djava.security.properties=file:config/java.security.additional -Dquarkus.http.host=0.0.0.0 -Dquarkus.config.locations=config/application.properties -Djava.util.logging.manager=org.jboss.logmanager.LogManager -Dsemeru.fips=true '--class-path=target/quarkus-app/lib/boot/*:target/quarkus-app/lib/main/*' -jar target/quarkus-app/quarkus-run.jar
__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2023-10-12 08:54:58,904 ERROR [io.qua.run.Application] (main) Failed to start application (with profile [prod]): java.lang.RuntimeException: Failed to start quarkus
	at io.quarkus.runner.ApplicationImpl.doStart(Unknown Source)
	at io.quarkus.runtime.Application.start(Application.java:101)
	at io.quarkus.runtime.ApplicationLifecycleManager.run(ApplicationLifecycleManager.java:111)
	at io.quarkus.runtime.Quarkus.run(Quarkus.java:71)
	at io.quarkus.runtime.Quarkus.run(Quarkus.java:44)
	at io.quarkus.runtime.Quarkus.run(Quarkus.java:124)
	at io.quarkus.runner.GeneratedMain.main(Unknown Source)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at io.quarkus.bootstrap.runner.QuarkusEntryPoint.doRun(QuarkusEntryPoint.java:61)
	at io.quarkus.bootstrap.runner.QuarkusEntryPoint.main(QuarkusEntryPoint.java:32)
Caused by: java.lang.RuntimeException: Unable to start HTTP server
	at io.quarkus.vertx.http.runtime.VertxHttpRecorder.doServerStart(VertxHttpRecorder.java:854)
	at io.quarkus.vertx.http.runtime.VertxHttpRecorder.startServer(VertxHttpRecorder.java:342)
	at io.quarkus.deployment.steps.VertxHttpProcessor$openSocket189362710.deploy_0(Unknown Source)
	at io.quarkus.deployment.steps.VertxHttpProcessor$openSocket189362710.deploy(Unknown Source)
	... 13 more
Caused by: java.util.concurrent.ExecutionException: java.security.KeyStoreException: Key protection algorithm not found: java.security.UnrecoverableKeyException: Encrypt Private Key failed: Error deriving PBKDF2 keys
	at java.base/java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:396)
	at java.base/java.util.concurrent.CompletableFuture.get(CompletableFuture.java:2073)
	at io.quarkus.vertx.http.runtime.VertxHttpRecorder.doServerStart(VertxHttpRecorder.java:775)
	... 16 more
Caused by: java.security.KeyStoreException: Key protection algorithm not found: java.security.UnrecoverableKeyException: Encrypt Private Key failed: Error deriving PBKDF2 keys
	at java.base/sun.security.pkcs12.PKCS12KeyStore.setKeyEntry(PKCS12KeyStore.java:709)
	at java.base/sun.security.pkcs12.PKCS12KeyStore.engineSetEntry(PKCS12KeyStore.java:1442)
	at java.base/sun.security.util.KeyStoreDelegator.engineSetEntry(KeyStoreDelegator.java:174)
	at java.base/java.security.KeyStore.setEntry(KeyStore.java:1585)
	at io.vertx.core.net.impl.KeyStoreHelper.loadKeyCert(KeyStoreHelper.java:296)
	at io.vertx.core.net.PemKeyCertOptions.getHelper(PemKeyCertOptions.java:421)
	at io.vertx.core.net.PemKeyCertOptions.getKeyManagerFactory(PemKeyCertOptions.java:439)
	at io.vertx.core.net.impl.SSLHelper.lambda$build$6(SSLHelper.java:246)
	at io.vertx.core.impl.ContextBase.lambda$executeBlocking$1(ContextBase.java:180)
	at io.vertx.core.impl.ContextInternal.dispatch(ContextInternal.java:277)
	at io.vertx.core.impl.ContextBase.lambda$internalExecuteBlocking$2(ContextBase.java:199)
	at io.vertx.core.impl.TaskQueue.run(TaskQueue.java:76)
	at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2513)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1512)
	at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
	at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:857)
Caused by: java.security.UnrecoverableKeyException: Encrypt Private Key failed: Error deriving PBKDF2 keys
	at java.base/sun.security.pkcs12.PKCS12KeyStore.encryptPrivateKey(PKCS12KeyStore.java:957)
	at java.base/sun.security.pkcs12.PKCS12KeyStore.setKeyEntry(PKCS12KeyStore.java:631)
	... 18 more
Caused by: java.lang.RuntimeException: Error deriving PBKDF2 keys
	at java.base/com.sun.crypto.provider.PBKDF2KeyImpl.deriveKey(PBKDF2KeyImpl.java:219)
	at java.base/com.sun.crypto.provider.PBKDF2KeyImpl.<init>(PBKDF2KeyImpl.java:130)
	at java.base/com.sun.crypto.provider.PBKDF2Core.engineGenerateSecret(PBKDF2Core.java:70)
	at java.base/com.sun.crypto.provider.PBES2Core.engineInit(PBES2Core.java:280)
	at java.base/com.sun.crypto.provider.PBES2Core.engineInit(PBES2Core.java:309)
	at java.base/javax.crypto.Cipher.implInit(Cipher.java:875)
	at java.base/javax.crypto.Cipher.chooseProvider(Cipher.java:929)
	at java.base/javax.crypto.Cipher.init(Cipher.java:1585)
	at java.base/javax.crypto.Cipher.init(Cipher.java:1516)
	at java.base/sun.security.pkcs12.PKCS12KeyStore.encryptPrivateKey(PKCS12KeyStore.java:937)
	... 19 more
Caused by: java.security.InvalidKeyException: init() failed
	at jdk.crypto.cryptoki/sun.security.pkcs11.P11Mac.engineInit(P11Mac.java:214)
	at java.base/javax.crypto.Mac.init(Mac.java:432)
	at java.base/com.sun.crypto.provider.PBKDF2KeyImpl.deriveKey(PBKDF2KeyImpl.java:191)
	... 28 more
Caused by: sun.security.pkcs11.wrapper.PKCS11Exception: CKR_KEY_SIZE_RANGE
	at jdk.crypto.cryptoki/sun.security.pkcs11.wrapper.PKCS11.C_SignInit(Native Method)
	at jdk.crypto.cryptoki/sun.security.pkcs11.P11Mac.initialize(P11Mac.java:183)
	at jdk.crypto.cryptoki/sun.security.pkcs11.P11Mac.engineInit(P11Mac.java:212)
	... 30 more
```
