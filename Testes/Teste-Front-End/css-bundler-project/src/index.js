// This file is the entry point of the application. It contains the logic to read all CSS files from the 'public/css' directory, concatenate them, and write the combined CSS into 'dist/bundle.css'.

const fs = require('fs');
const path = require('path');

// Directory containing the original CSS files
const cssDirectory = path.join(__dirname, '../public/css');

// Output file for the combined CSS
const outputFile = path.join(__dirname, '../dist/bundle.css');

// Function to bundle CSS files
function bundleCSS() {
    fs.readdir(cssDirectory, (err, files) => {
        if (err) {
            console.error('Error reading CSS directory:', err);
            return;
        }

        const cssFiles = files.filter(file => file.endsWith('.css'));
        let combinedCSS = '';

        cssFiles.forEach(file => {
            const filePath = path.join(cssDirectory, file);
            const cssContent = fs.readFileSync(filePath, 'utf-8');
            combinedCSS += cssContent + '\n'; // Add a newline for separation
        });

        fs.writeFileSync(outputFile, combinedCSS, 'utf-8');
        console.log('CSS files have been bundled into', outputFile);
    });
}

// Execute the bundling function
bundleCSS();