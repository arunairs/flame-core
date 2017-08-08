import path from "path"

export default {
    entry: "./src/main.js",
    output: {
        path: path.resolve(`./dist/${Date.now()}/`),
        publicPath: "/static/",
        filename: "build.js"
    }
}