mkdir doccheck

javadoc -doclet com.sun.tools.doclets.doccheck.DocCheck ^
-docletpath .\lib\doccheck.jar ^
-d .\doccheck ^
-sourcepath .\src ^
-subpackages juego ^
-encoding UTF-8