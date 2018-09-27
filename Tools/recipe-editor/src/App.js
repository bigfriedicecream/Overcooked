import React, { Component } from 'react';
import './App.css';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import data from './recipes.json'

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data
        }
    }

    handleChange = (key, field) => e => {
        const data = Object.assign({}, this.state.data);
        data.recipes[key][field] = e.target.value
        this.setState({ data });
    }

    render() {
        const { recipes } = this.state.data
        return (
            <div className="App">
                {Object.keys(recipes).map(key => {
                    return (
                        <TextField key={key} label="Title" margin="normal" fullWidth value={recipes[key].title} onChange={this.handleChange(key, 'title')} />
                    )
                })}
                <Button variant="contained" color="primary">Save</Button>
                <br /><br />
                <TextField label="Output" margin="normal" value={JSON.stringify(this.state.data)} variant="outlined" />
            </div>
        );
    }
}

export default App;
