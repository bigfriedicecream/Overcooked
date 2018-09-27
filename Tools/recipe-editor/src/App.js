import React, { Component } from 'react';
import './App.css';
import data from './recipes.json';

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
            <div>
                {Object.keys(recipes).map(key => {
                    const recipe = recipes[key];
                    return (
                        <div>
                            <div>
                                title:
                                <input type="text" value={recipe.title} onChange={this.handleChange(key, 'title')} />
                            </div>
                            <div>
                                serves:
                                <input type="number" value={recipe.serves} onChange={this.handleChange(key, 'serves')} />
                                makes:
                                <input type="number" value={recipe.makes} onChange={this.handleChange(key, 'makes')} />
                                prepTime:
                                <input type="number" value={recipe.prepTime} onChange={this.handleChange(key, 'prepTime')} />
                                cookTime:
                                <input type="number" value={recipe.cookTime} onChange={this.handleChange(key, 'cookTime')} />
                            </div>
                            <div>
                                Ingredients
                                {recipe.ingredients.map((ing, i) => {
                                    return (
                                        <div key={`recipe-${key}ing-${i}`}>
                                            <input type="number" value="1" />
                                            <select>
                                                <option value="op1">Option 1</option>
                                                <option value="op2">Option 2</option>
                                                <option value="op3">Option 3</option>
                                            </select>
                                            <input type="text" value="Recipe Display" />
                                        </div>

                                    )
                                })}
                            </div>
                            <div>
                                Method
                            </div>
                            <br /><hr /><br />
                        </div>
                    )
                })}
                <br />
                <button>+</button>
                <br /><br /><br />
                <input type="text" value={JSON.stringify(this.state.data)} />
            </div>
        );
    }
}

export default App;
