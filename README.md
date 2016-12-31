# Android-Code-Report-Generator

This is a simple Android application project created for educational purposes, called "CodeReportGenerator". This offline application is capable to hold and show multiple -codes/passwords- for every day of the year. This way you will have organized a group of -codes/passwords- that can be used for a variety of reasons. For example, you want specific members or colleagues to have access to a system that changes password every day. If the application is stored and executed in multiple devices, then multiple users will know the same information and can use it for the same purpose. Because this application is completely offline, using SQLite, your data are safe in your phone. 

This project consists of two main parts. The first part is a java program that is capable to generate random -codes/passwords- for every day of the year "JRndCodeGen.java". This code uses the "java.time" package for the date and the "java.util.Random" packet for generating random -codes/passwords- for every day. The second part is the Android application that is responsible to hold and illustrate the -codes/passwords- depending of the input day or day period. 
/*** Usage ***/

-JRndCodeGen.java-

This program is responsible to generate random 4-digit -codes/passwords- (you can change the length if you want to) for every day of the period that the user defines. In more details it holds two dates in a specific format and can extract random -codes/passwords- in the desired format. This format fits perfectly to the application needs, though after the generation you can simply copy-paste the generated codes in the application before building it. Of course you can generate your own -codes/passwords- depending of the requirments of your project. The format of every -code/password- in the Console-Output follows a format that is ready to be insert in the application.

format example	==>  addSQLinfo(new SQLCodes("01-01-2017", "721", "471"));

After the completion of the -codes/passwords- generation you can copy-paste these random (or your own) -codes/passwords- to the file DatabaseHandler.java inside the application and build it. In addition, if for example your codes are stored in a file, you could create your own java program to fetch the -codes/passwords- in the aforementioned format.

-CodeReportGenerator (Android application)-

The application consists of five .java source files. It uses SQLite for offline -code/password- storage and is capable to present specific -codes/passwords- for specific dates. Once your information are in place you can just build the application and extract the .apk file. During the creation of the .apk file you can ensure the safety of the database by inserting your own passcode, so only you will have access in it. In order to use the program you will have first to input the desired dates. You can do that by pressing the "FROM DATE" and "TO DATE" buttons. By pressing these buttons the calendar context is called for you to choose the specific date range and the dates are printed next to the buttons. By pressing the "FOR TODAY" button the application is reading the current date and printing that instead. By completing the desired dates, you can press the "CALCULATE" button to fetch the passwords from the SQLite database in a new screen. Finally, by pressing the "EXIT" button the application will exit and by pressing the logo at the top the application will reset it's fields.

In application project files, an already singed build (.apk) is attached as a demo for your use in root "CodeReportGenerator\app\CRG.apk". The -codes/passwords- in this demo are valid from 2017-01-01 to 2018-01-01 but you can set your own dates in the database.

-Emulator Samples-

![alt tag](https://github.com/kmonahopoulos/Android-Code-Report-Generator/EmuAppPresentations/EmuAppPresentation_1.png)