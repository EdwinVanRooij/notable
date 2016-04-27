echo off

rem Declare date variables
set year=%date:~-4,4%
set month=%date:~-10,2%
set day=%date:~-7,2%
set hour=%time:~-11,2%
set hour=%hour: =0%
set min=%time:~-8,2%

rem Set the date string for commit message
set datestring="%year%-%month%-%day%_%hour%.%min%"

rem Add all files to the git head
git add .

rem Commit all changes
git co -m "Updated the config files at %datestring% - (automated commit from batch)"

rem Push to remote git repo
git push