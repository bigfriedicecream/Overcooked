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
                            <div class="form-group">
                                <label>Title</label>
                                <input type="text" className="form-control" value={recipe.title} onChange={this.handleChange(key, 'title')} />
                            </div>
                            <div class="form-group">
                                <label>Serves</label>
                                <input type="number" className="form-control" value={recipe.serves} onChange={this.handleChange(key, 'serves')} />
                            </div>
                            <div class="form-group">
                                <label>Makes</label>
                                <input type="number" className="form-control" value={recipe.makes} onChange={this.handleChange(key, 'makes')} />
                            </div>
                            <div class="form-group">
                                <label>Prep Time</label>
                                <input type="number" className="form-control" value={recipe.prepTime} onChange={this.handleChange(key, 'prepTime')} />
                            </div>
                            <div class="form-group">
                                <label>Cook Time</label>
                                <input type="number" className="form-control" value={recipe.cookTime} onChange={this.handleChange(key, 'cookTime')} />
                            </div>
                            <div>
                                Ingredients
                                {/*recipe.ingredients.map((ing, i) => {
                                    return (
                                        <div key={`recipe-${key}ing-${i}`}>
                                            <div class="form-group">
                                                <label>Quantity</label>
                                                <input type="number" className="form-control" value="1" />
                                            </div>
                                            <div class="form-group">
                                                <label>Ingredient Type</label>
                                                <select className="form-control">
                                                    <option>op1</option>
                                                    <option>op2</option>
                                                    <option>op3</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Recipe Display</label>
                                                <input type="text" className="form-control" value="Recipe Display" />
                                            </div>
                                        </div>

                                    )
                                })*/}
                            </div>
                            {/*<div>
                                Method
                            </div>*/}
                            <br /><hr /><br />
                        </div>
                    )
                })}
                <br />
                <button type="button" className="btn btn-primary">+</button>
                <br /><br /><br />
                <div class="form-group">
                    <label>Output</label>
                    <input type="text" className="form-control" value={JSON.stringify(this.state.data)} />
                </div>
            </div>
        );
    }
}

export default App;
