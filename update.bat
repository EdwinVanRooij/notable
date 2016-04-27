echo off

rem Declare date variables
set year=%date:~-4,4%
set month=%date:~-10,2%
set day=%date:~-7,2%
set hour=%time:~-11,2%
set hour=%hour: =0%
set min=%time:~-8,2%

rem Show some meta info
echo "Currently at %cd%"

rem Add all files to the git head
echo "Adding all files to git head..."
git add .

rem Commit all changes
echo "Committing changes..."
git commit -m "Added all changes at %year%-%month%-%day%_%hour%.%min% - (automated commit from batch)"

rem Push to remote git repo
echo "Pushing changes..."
git push