# monacojx
## purpose 
In this project the jxbrowser https://www.teamdev.com/jxbrowser is used to integrate monaco editor https://microsoft.github.io/monaco-editor/ 
in a JavaFx application.

## description
The JavaScript code of monaco editor is included in <code>src/main/resources/de/softquadrat/monacojx/monaco-editor/package</code> directory. 
At the start of the application the <code>src/main/resources/de/softquadrat/monacojx/monaco-editor/index.html</code> is loaded
and the JavaScript code of monaco editor is executed in the jxbrowser.

## licence key
To start the application you need a licence key for jxbrowser. 

## start by maven
<code>mvn javafx:run -Djxbrowser.license.key=<license key></code>