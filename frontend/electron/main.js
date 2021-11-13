const { app, BrowserWindow, screen: electronScreen,ipcMain,dialog } = require('electron');
const path = require('path')
const cp = require('child_process')
let spawnedVTBox = false;
let mainWindow;
const createMainWindow = () => {
    console.log(__dirname)

    mainWindow = new BrowserWindow({
        width: electronScreen.getPrimaryDisplay().workArea.width,
        height: electronScreen.getPrimaryDisplay().workArea.height,
        show: false,
        backgroundColor: 'white',
        webPreferences: {
            nodeIntegration: true,
            enableRemoteModule:true,
            contextIsolation: false,
        }
    });
    const startURL = 'http://localhost:3000';

    mainWindow.loadURL(startURL);

    // mainWindow.webContents.openDevTools()

    function runJar() {
            const cp = require('child_process')
            const jarPath = path.join(app.getAppPath(),"OWL2Bench.jar")
            var child = cp.spawn( 'java', ['-jar', jarPath, '1','DL'])

            var kill = require('tree-kill');
            kill(child.pid);
    }

    mainWindow.once('ready-to-show', () => {
            
            mainWindow.show()
    });

    mainWindow.on('closed', () => {
        mainWindow = null;
    });
};

app.whenReady().then(() => {
    createMainWindow();

    app.on('activate', () => {
        if (!BrowserWindow.getAllWindows().length) {
        createMainWindow();
        }
    });
});

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

ipcMain.on('select-dirs', async (event, arg) => {
    console.log("HIIIIIIIIIIIIIIII")
    const result = await dialog.showOpenDialog(mainWindow, {
      properties: ['openDirectory']
    })
    console.log('directories selected', result.filePaths)
    event.sender.send('directorySelection', {'Path': result.filePaths});
})

ipcMain.on('start-VariableTBox' ,(event,arg) => {
    console.log(event,arg);
    if (!spawnedVTBox){
        const jarPath = path.join(app.getAppPath(),"VariableTBox")
        const jar = path.join(jarPath,"backend-0.0.1-SNAPSHOT.jar")
        var child = cp.spawn( 'java', ['-jar', jar] ,{cwd:jarPath});
        
        spawnedVTBox=true;
        
        child.stdout.on('data', (data) => {
            console.log(`stdout: ${data}`);
        });

        child.on('error' ,(code) => {
            console.log(`child process errored with code ${code}`)
            console.log()
        })      
    
        child.on('exit', (code) => {
            console.log(`child process VTBox exited with code ${code} and parameters`);
            console.log(arg)
        });
    }        
});
// Event for Fixed TBox
ipcMain.on('generate-FixedTbox', (event, arg) => {
    const jarPath = path.join(app.getAppPath(),"FixedTBox")
    const jar = path.join(jarPath,"OWL2Bench.jar")
    if (arg.seed === -1){
        var child = cp.spawn( 'java', ['-jar', jar, arg.univ, arg.profile],{cwd:jarPath})
    }
    else {
        var child = cp.spawn( 'java', ['-jar', jar, arg.univ, arg.profile, arg.seed],{cwd:jarPath})
    }

    child.stdout.on('data', (data) => {
        console.log(`stdout: ${data}`);
    });

    child.on('error' ,(code) => {
        console.log(`child process errored with code ${code}`)
        console.log()
    })      
    
    child.on('exit', (code) => {
        console.log(`child process exited with code ${code} and parameters`);
        console.log(arg)
    });
});