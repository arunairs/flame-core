module.exports = {
    entry: './src/main.js',
    output: {
        path: __dirname + './dist',
        publicPath: '/static/',
        filename: 'bundle.js'
    }
}