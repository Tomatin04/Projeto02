# CSS Bundler Project

This project is designed to combine multiple CSS files from the `public/css` directory into a single CSS file located in the `dist` directory. The bundler script reads all CSS files, concatenates their contents, and outputs the result to `dist/bundle.css`.

## Project Structure

```
css-bundler-project
├── src
│   └── index.js          # Entry point for the bundler script
├── public
│   └── css               # Directory containing original CSS files
├── dist
│   └── bundle.css        # Output file containing combined CSS
├── package.json          # npm configuration file
└── README.md             # Project documentation
```

## Getting Started

To get started with this project, follow these steps:

1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd css-bundler-project
   ```

2. **Install dependencies**:
   Make sure you have Node.js installed. Then run:
   ```
   npm install
   ```

3. **Add your CSS files**:
   Place all your CSS files in the `public/css` directory.

4. **Run the bundler**:
   Execute the bundler script to combine the CSS files:
   ```
   node src/index.js
   ```

5. **Check the output**:
   After running the script, you will find the combined CSS in `dist/bundle.css`.

## Dependencies

This project may require the following npm packages:
- `fs` for file system operations
- `path` for handling file paths

Make sure to include these in your `package.json` file.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.