## How to use

 * lib/springmvc-gen-XXX.jar
 * build.xml (copy from dest/build.xml and edit properties)


    $ ant gen-controller

after input base-path, generates `XxxController`, `XxxControllerTest`, `index.jsp` at the package following the rule

    $ ant gen-service

after input service-name, generates `XxxService`, `XxxServiceImpl`, `XxxServiceImplTest` at the package following the rule

    $ ant gen-service-without-interface

after input service-name, generates `XxxService`, `XxxServiceTest` at the package following the rule

    $ ant gen-form-model

after input form-name, generates `XxxForm`, `XxxFormTest` at the package following the rule

    $ ant gen-output-model

after input form-name, generates `XxxOutput`, `XxxOutputTest` at the package following the rule