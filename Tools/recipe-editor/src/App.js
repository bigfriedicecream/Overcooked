import React, { Component } from 'react';
import './App.css';
import data from './recipes.json';
import Recipe from './Recipe/Recipe';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data
        }
    }

    onFieldChange = (id, field) => e => {
        const data = Object.assign({}, this.state.data);
        data.recipes[id][field] = e.target.value
        this.setState({ data });
    }

    render() {
        const { recipes } = this.state.data
        const handlers = {
            onFieldChange: this.onFieldChange
        }
        return (
            <div>
                {Object.keys(recipes).map(key => {
                    const recipe = recipes[key];
                    return (
                        <Recipe recipe={recipe} handlers={handlers} />
                    )
                })}
                <button type="button" className="btn btn-primary">+</button>
                <br /><br />
                <div className="form-group">
                    <label>Output</label>
                    <input type="text" className="form-control" readOnly value={JSON.stringify(this.state.data)} />
                </div>
            </div>
        );
    }
}

export default App;
