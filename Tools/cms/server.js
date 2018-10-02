const express = require('express');
var bodyParser = require("body-parser");
var fs = require("fs"), json;

const app = express();
const port = process.env.PORT || 5000;

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get('/api/hello', (req, res) => {
    var file = fs.readFileSync('./overcooked.json', 'utf8');
    res.send(JSON.parse(file));
});

app.post('/recipes/save', (req, res) => {
    var file = fs.writeFileSync('./overcooked.json', JSON.stringify(req.body), { encoding: 'utf8' });
    res.send(file);
});

app.listen(port, () => console.log(`Listening on port ${port}`));