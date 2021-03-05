# VolTextGUI
https://gluonhq.com/products/javafx/    JAVAFX lib

Include the new JDK as Installed JREs in Eclipse -> Preferences -> Java -> Installed JREs -> Add.

Include in the project the jars under the lib folder from JavaFX 15.

Click on Run -> Run Configurations...  -> Java Application, create a new launch configuration for your project named `hellofx` and add these VM arguments:

--module-path \Users\Utente\Desktop\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.fxml
