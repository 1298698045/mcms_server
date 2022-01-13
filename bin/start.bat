@echo off

start javaw -Dloader.path=lib,config -jar -Dfile.encoding=utf-8 ^
-Duser.timezone=GMT+08 ./ms-mcms.jar
:: > app.log 2>&1
exit