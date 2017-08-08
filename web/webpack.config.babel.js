import path from "path"
module.exports = {
    entry: "./src/main.js",
    output: {
        path: path.resolve(`./dist/${Date.now()}/`),
        publicPath: "/static/",
        filename: "build.js"
    }
}