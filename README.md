# Overview #
## Details/Assumptions made before solving this challenge. ##

The website chosen for constructing web automation is a travel booking website - "https://www.check24.de/" and the android app used is the standard app present as part of challenge guidelines. Though these are completely different applications now, it was assumed that there could be a relation between the app and website as in almost all real-time projects. Also, it might be needed to share test-data or files across the tests for these in the future.
	
This vision has led to the thought of having web and mobile automated tests and their drivers in the same project as three packages - framework, driver, and tests.

### framework ###
The core of the project which has sub-packages for the following.
* configuration - To read the configuration in an efficient manner and present it to other classes.
* core - To provide the basic support to design web and mobile page object classes and tests.
* factory - To kickstart and initiate the choice of browser or mobile app as per the configurations made by the user.
* utils - To provide the common utils needed for tests or driver. Examples include misc test data utils, domain-specific data generation & parsing, etc.

### driver ###
The part of the project having classes that actually help to drive through the web UI or native app UI. It is categorized to
* mobility - The page object classes for the mobile app. Each is designed to inherit features from 'framework.core.mobility.MobilityBasePage.
* web - The page object classes for the different pages in web application. Each is designed to inherit from 'framework.core.web.BasePage'.
	
### tests ###
This part of the project holds the tests for web and mobile in respective packages. They also contain some XML files for test runs categorization.
* mobility - Holds the tests for the native android mobile application. Tests are categorized as per the functionality they validate.
* web - Holds the tests for the web app. Tests are categorized as per the functionality they validate.
* resources.testngXmls - Holds the TestNG XML files that run different test suites based on need - web or mobile.

Note: To contribute and develop new tests, only minimal or intermediate programming skills are required due to the fluent interface design approach.
	
	
A possible disadvantage is that as the number of tests or driver classes increases over time, there could a significant delay in compilation time. 
A solution to the above-said problem would be to spinoff the framework project & segregate the mobile and web parts from driver and tests, each making use of the framework project as a dependency. But this is not a case of worry unless the driver + tests classes grow to a big number.

***Future Possible Evolution from here for this project is as shown below***
* MultiModule project for the mobile test alone -  common framework dependency+ (mobile driver + mobile tests)
* Multimodule project for web test alone  - common framework dependency + (web app driver + web app tests)

# Installation #
## Preconditions Mandatory for running the test ##
### Install Java and add to path ###
* Download and install JDK (1.8 preferred) from https://www.java.com install it add to the path variable.
* One should be able to type 'java -version' from the terminal and get the valid response with version details like "java version "1.8.0_131". For eg., my path contains "C:\Program Files\Java\jdk1.8.0_131\bin".
### Install maven and add to path ### 
* Download the maven package from "https://maven.apache.org/download.cgi". Extract it and add the location to the path variable.
* One should be able to type 'mvn -version' from the terminal and ger the valid response with version details like "Apache Maven 3.3.9". For instance, I have used maven 3.3.9 and my path variable has "C:\Program Files\apache-maven-3.3.9\bin;"
### Install Google Chrome or Mozilla Firefox [applicable only to web tests] ###
* Download and install Google Chrome (or Mozilla Firefox) browsers. I have downloaded and used Google Chrome Version 75.0.3770.142 (Official Build) (64-bit) and concluded that all tests work well in it. One could also use Mozilla Firefox if they want.
* Based on your choice of browser, download the drivers. ChromeDriver for Google Chrome (or GeckoDriver if Mozilla Firefox was chosen). This step is a very important as the Driver should be compatible with your browsers version. The latest version of stable Chrome Driver can be downloaded from "https://sites.google.com/a/chromium.org/chromedriver/"
* Download the path and take note of the location of the exe. We would want that to add to configuration files.
### Install and setup Appium for mobile [applicable only to web tests]. (Please be patient as this requires a handful of one-time configuration) ###
* Install Android SDK (android studio is not necessary) from "https://developer.android.com/studio". Go to "Command line tools only" section and select the package based on your operating system. Extract the package to a location say "D:\android_autom" and add the following to the path - "D:\android_autom\tools\bin;" . This will enable you to use 'sdkmanager' and 'uiautomatorviewer' from the terminal.
* One could verify the above installation by typing "sdkmanager --list" in a terminal and it should display the list of installed packages (only Android SDK tool as of now).
* Now it is time to install the necessary packages into the sdk. This is dependent on the Android version of the real device you intend to run the tests. My test device is of 'Android 5.1.1 aka API22'. If you have an Android version different than mine in your device. Please look at this webpage "https://developer.android.com/guide/topics/manifest/uses-sdk-element.html" to find the correct API Level and take a note of it. Using the command "sdkmanager --list" would print all available options in front of the user.
* To install the package, use the command "sdkmanager "platform-tools" "platforms;android-22" --verbose". Please use a different code if your API Level is different from 22.
* Now it is time to install the Android Build tools. I had installed the version "build-tools;28.0.0", but there are newer versions one could use. Use the command "sdkmanager "build-tools;28.0.0" --verbose " to install the same.
* Add a system environment variable "ANDROID_HOME=D:\android_autom" which is the home of the android sdk we extracted in first step of installation. Also add the location "ANDROID_HOME\platform_tools" to path. This will let you use 'adb' command from a terminal which is very handy while working with appium.
* If you connect you device via usb to the computer such that device has 'Developer options enabled', 'USB Debugging enabled', you would able to get the connected device listed when you type "adb devices" from any terminal. If you face any error like "com.android.ddmlib.AdbCommandRejectedException: device unauthorized." while trying the command, please restart the adb by typing 'adb kill-server' & 'adb start-server' and try 'adb devices' again. It is always recommended to keep devices with options 'Stay Awake enabled', 'Skip Screen lock enabled' for smooth test execution.
* Now place the mobile app to be installed & tested in a location in your computer. Say 'D:\packages\QA_App.apk'. Open a terminal from that location and run the command 'adb install QA_App.apk' to install the app to the device. You would get a 'success' message in response.
* The Appium works on Node.js. So please install Node.js by visiting "https://nodejs.org/en/download/". Also kindly add that to the system path variable. For eg, I had it added to path as - "C:\node.js-v10.15.3;" .Now you should be able to use npm - Node's package manager.
* To install appium using npm, open terminal and run the command "npm install -g appium". After successful installation, start the appium server by using the command appium -a 127.0.0.1 -p 4723 ,where -a is the ip address (localhost in this case) and -p is the port where appium listens.
* Note that appium server can also be started manually if needed by downloading appium app from "http://appium.io/", launch it and start the server by clicking the 'Start server' button. If you prefer that, you could skip the above step(of installing via npm). Keep the URL to appium server handy as it has to be supplied to the project configuration later.
## How to run the tests in your local machine ##
### To execute test for web application ###
* Open a terminal, preferably git bash. Others like Powershell would also work equally fine.
* Run "git clone https://github.com/maneesh-ms/BabbelChallenge.git" to grab the project.
* Open the file 'runconfiguration.properties' from the project home directory and edit the values for 'browser'. Leave it as such if you wish to run in Chrome.
* Edit the value of 'chromeDriverLocation' to the location in your system where ChromeDriver is present and save the file 'runconfiguration.properties'.
* From the terminal,  navigate to the folder 'BabbelChallenge', run the below command
    "mvn clean test -Dsurefire.suiteXmlFiles=./src/test/java/resources/testngxmls/web/web_all_tests_suite.xml"
* Once the test run is over, Run "mvn allure:serve" to serve the Run Results in your default browser.
* Go to 'Suites' in the left side to see the list of results. Also take a look at the graphs for Status and Severity.
* Note:
    * The normal test run would have 3 tests with 2 passed and One Failed. The failed one is intentional to check the screenshot feature and reporting.
    * The report generation for first time might take a minute due to first time downloading of dependencies.
### To execute test for mobile application ###
* Open a terminal, preferably git bash. Others like PowerShell would also work equally fine.
* Run "git clone https://github.com/maneesh-ms/BabbelChallenge.git" to grab the project.
* Ensure the device are connected to the computer. Running 'adb devices' would list the device is.
* Open the file 'runconfiguration.properties' from the project home directory and edit the values for 'appiumServer'(if you have same address leave it as such) and 'deviceName' (from the output of adb devices in the above step) and save the changes.
* From the terminal, navigate to the folder 'BabbelChallenge', run the below command
    "mvn clean test -Dsurefire.suiteXmlFiles=./src/test/java/resources/testngxmls/mobility/mobile_all_tests_suite.xml"
* Once the test run is over, run "mvn allure:serve" to serve the Run Results in your default browser.
* Go to 'Suites' in the left side to see the list of results. Also take a look at the graphs for Status and Severity.
* Note:
    * The normal test run would have a set of 5 tests in total with 4 passed and One Failed. The failed one is intentional to check the screenshot feature and reporting.
    * The report generation for first time might take a minute due to first time downloading of dependencies.
# Frameworks/Tools/Patterns used #

## Frameworks Used in this project ##
### Why Selenium was chosen for this project ###
#### Pros ####
* Most methods are robust nowadays in handling almost all web related scenarios.
* The same code written could easily run on several browsers like Chrome, Firefox and IE, provided one has the right drivers.
* Being an open-source project, we could fix any problems if we wish to. The GitHub community for selenium is active and lively too.
#### Cons ####
* The client-server architecture of Selenium makes adds an element of slowness to the tests. But this is okay if we have enough resources to run tests.
* When compared to commercial test automation tools like UFT, the support for issues would take little bit more time.
* While using Selenium, one has to invest time to build the framework around it like my implementation here. integrating several libraries like TestNG, allure, extend reports, etc to facilitate state and easy development of tests.
### Why Appium Used For Mobile Tests in this project ###
#### Pros ####
* Appium, in general, is an extension of Selenium with classes like AndroidDriver and IOSDriver to handle mobile-related scenarios. If you have a good framework around selenium for handling web-related scenarios, those could be easily leveraged to handle mobile screens. For example in this project, the editing of text boxes and clicking elements of mobile screens use the same code as used in the web.
* If there exists a need to automated an ios app, similar to the native android app, it is easy to make tests run with little changes. This is one benefit of working with Appium
#### Cons ####
* As a descendant of Selenium, the Appium framework is slow compared to other frameworks like espresso. So if there is no related web application to the native app or there are independent teams for ios and android, it is probably useful to build a framework around espresso.
### Why TestNG was chosen to be used in tests ###
#### Pros ####
* TestNG community in GitHub is very active. They are always keen on adding new features and encourage contribution from others including me. I have fixed a couple of issues in this library and was very familiar. So I used it in this project.
* It is easy to add checkpoints in tests with a great variety of options in Assert class.
* TestNG has great listeners fit to be used for reporting in this project.
#### Cons ####
* There are still plenty of open issues reported with this framework. One might face one of them during active development and may eat sometime.
### Why allure-TestNG  for reporting ###
#### Pros ####
* Allure has very intuitive UI screens with graphs. Moreover, it has a good amount of detailing with a screenshot. Thus it could give a clear indication of the test run status and what went wrong in the event of a failure.

## Design Patterns Used in this project ##
### Factory Pattern ###
  This is used in the framework to generate the desired browser or launch the desired app Based on user configuration. There are a couple of classes Browser Factory and DeviceFactory in the framework. This would help to keep the conditional logic restricted inside factory classes.
### Page Object Model ###
  All the pages on the web and screens of mobile have been modeled into Page Object Classes. A Page Object Class Comprises of page/screen location parameters like By in Selenium. This is relatively easy to manage than keyword driven frameworks. Also, there could be a great deal of code reusability when several pages inherit common components for Headers, Navigation Bars.
### Fluent Interface Pattern In Tests ###
  The tests are designed with a Fluent interface Pattern. This helps test developers with beginner to intermediate programming skills. If an IDE is being used, the auto IntelliSense would prompt all available options to the test designer, thus helping to develop the flow easily.