artifacts builderVersion:"1.1", {
  if (buildRuntime=="ntamd64" ) {
    group "org.cloudfoundry.identity", {
        artifact "cloudfoundry-identity-server", {
            file "server/build/libs/cloudfoundry-identity-server-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-model", {
            file "model/build/libs/cloudfoundry-identity-model-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-client-lib", {
            file "client-lib/build/libs/cloudfoundry-identity-client-lib-${buildBaseVersion}.jar", extension: "jar"
        }
    }
  } else if  ( buildRuntime=="linuxx86_64" ) {
    group "org.cloudfoundry.identity", {
        artifact "cloudfoundry-identity-server", {
            file "server/build/libs/cloudfoundry-identity-server-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-model", {
            file "model/build/libs/cloudfoundry-identity-model-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-client-lib", {
            file "client-lib/build/libs/cloudfoundry-identity-client-lib-${buildBaseVersion}.jar", extension: "jar"
        }
    }
  } else if  ( buildRuntime=="linuxppc64" ) {
    group "org.cloudfoundry.identity", {
        artifact "cloudfoundry-identity-server", {
            file "server/build/libs/cloudfoundry-identity-server-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-model", {
            file "model/build/libs/cloudfoundry-identity-model-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-client-lib", {
            file "client-lib/build/libs/cloudfoundry-identity-client-lib-${buildBaseVersion}.jar", extension: "jar"
        }
    }
  } else if  ( buildRuntime=="darwinintel64" ) {
    group "org.cloudfoundry.identity", {
        artifact "cloudfoundry-identity-server", {
            file "server/build/libs/cloudfoundry-identity-server-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-model", {
            file "model/build/libs/cloudfoundry-identity-model-${buildBaseVersion}.jar", extension: "jar"
        }
        artifact "cloudfoundry-identity-client-lib", {
            file "client-lib/build/libs/cloudfoundry-identity-client-lib-${buildBaseVersion}.jar", extension: "jar"
        }
    }
  }
}
