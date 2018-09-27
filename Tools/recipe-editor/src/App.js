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
                        <div key={`recipe-${recipe.id}`} className="card">
                            <div class="card-body">
                                <h5 class="card-title">{recipe.title}</h5>
                                <div className="form-group">
                                    <label>Title</label>
                                    <input type="text" className="form-control" value={recipe.title} onChange={this.handleChange(key, 'title')} />
                                </div>
                                <div className="d-flex">
                                    <div className="form-group">
                                        <label>Serves</label>
                                        <input type="number" className="form-control" value={recipe.serves} onChange={this.handleChange(key, 'serves')} />
                                    </div>
                                    <div className="form-group">
                                        <label>Makes</label>
                                        <input type="number" className="form-control" value={recipe.makes} onChange={this.handleChange(key, 'makes')} />
                                    </div>
                                    <div className="form-group">
                                        <label>Prep Time</label>
                                        <input type="number" className="form-control" value={recipe.prepTime} onChange={this.handleChange(key, 'prepTime')} />
                                    </div>
                                    <div className="form-group">
                                        <label>Cook Time</label>
                                        <input type="number" className="form-control" value={recipe.cookTime} onChange={this.handleChange(key, 'cookTime')} />
                                    </div>
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
                            </div>
                        </div>
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
