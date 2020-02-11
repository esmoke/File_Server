# File Server #
This small groovy script runs **undertow** server in a directory listing mod.
This allows users which are in the same net to browse your local file system 
and download files which sometimes is useful for file sharing. 
 
 # Installation #
Run script *setup.sh* and open new terminal window.
``` bash
./setup.sh
```
which will:
1. Create **.esman** directory in the home directory of your user.
2. Copy required groovy script.
3. Create *share* alias in *.bash_aliases* file. 


# Usage #
In your terminal type:
```bash
share
```
in the directory which you want to share and open link which will be printed out: 
```bash
INFO: Share server started. Open link to browse and download files:
http://192.168.100.155:41397
```
Port will vary per each execution of the command.   

