# Full suite exection script
# executes the suite and then send mail report and then triggers allure report

#cd to project root folder, change it to your respective folder
Set-Location C:\Users\devbase\Projects\opencart\

mvn clean test "-DsuiteXmlFile=master.xml"

#while (Get-Process -Name "java" -ErrorAction SilentlyContinue) {
#    Start-Sleep -Seconds 2
#}

allure generate

allure open

#allure open