import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import { Link } from 'react-router-dom';
import Root from './Root';
import RecipeList from './recipe/RecipeList';
import Recipe from './recipe/Recipe';
import IngredientList from './ingredient/IngredientList';
import Ingredient from './ingredient/Ingredient';
import { IngredientModel } from '../models/IngredientModel';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: null
        }
    }

    componentDidMount() {
        this.callApi()
            .then(res => this.setState({ data: res }))
            .catch(err => console.log(err))
    }

    callApi = async() => {
        const response = await fetch('/api/hello');
        const body = await response.json();

        if (response.status !== 200) throw Error(body.message);

        return body;
    }

    onSave = async() => {
        const output = JSON.stringify(Object.assign({}, this.state.data));

        const response = await fetch('/recipes/save', {
            method: 'POST',
            body: output,
            headers: { "Content-Type": "application/json" }
        });

        if (response.status !== 200) throw Error(response.statusText);

        return true;
    }

    onRecipeFieldChange = (id, field) => e => {
        const data = Object.assign({}, this.state.data);

        var targetValue = e.target.value;

        if (e.target.type === 'number' || e.target.type === 'select-one') {
            targetValue = parseFloat(e.target.value)
        }

        data.recipes[id][field] = targetValue

        this.setState({ data });
    }

    onIngredientFieldChange = (id, field) => e => {
        const data = Object.assign({}, this.state.data);

        var targetValue = e.target.value;

        if (e.target.type === 'number' || e.target.type ===  'select-one') {
            targetValue = parseFloat(e.target.value)
        }

        data.ingredients[id][field] = targetValue

        this.setState({ data });
    }

    onAddIngredient = () => {
        const data = Object.assign({}, this.state.data);
        const ingredientModel = IngredientModel();
        data.ingredients[ingredientModel.id] = ingredientModel;
        this.setState({ data });
    }

    onRemoveIngredient = id => e => {
        const data = Object.assign({}, this.state.data);
        delete data.ingredients[id];
        this.setState({ data });
    }

    onRecipeMethodChange = (id, index) => e => {
        const data = Object.assign({}, this.state.data);
        data.recipes[id].method[index] = e.target.value;
        this.setState({ data });
    }

    render() {
        const handlers = {
            onRecipeFieldChange: this.onRecipeFieldChange,
            onIngredientFieldChange: this.onIngredientFieldChange,
            onAddIngredient: this.onAddIngredient,
            onRemoveIngredient: this.onRemoveIngredient,
            onRecipeMethodChange: this.onRecipeMethodChange
        }

        return (
            <div>
                <nav className="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
                    <div className="navbar-nav">
                        <Link to={'/recipe/list'} className="nav-link">Recipe list</Link>
                        <Link to={'/ingredient/list'} className="nav-link">Ingredient list</Link>
                    </div>
                    <div className="navbar-nav ml-auto">
                        <button type="button" className="btn btn-primary" onClick={this.onSave}>Save</button>
                    </div>
                </nav>
                <Switch>
                    <Route exact path='/' component={Root} />
                    <Route path="/recipe/list" render={routeProps => <RecipeList {...routeProps} data={this.state.data} handlers={handlers} />} />
                    <Route path="/recipe/edit/:id" render={routeProps => <Recipe {...routeProps} data={this.state.data} handlers={handlers} />} />
                    <Route path="/ingredient/list" render={routeProps => <IngredientList {...routeProps} data={this.state.data} handlers={handlers} />} />
                    <Route path="/ingredient/edit/:id" render={routeProps => <Ingredient {...routeProps} data={this.state.data} handlers={handlers} />} />
                </Switch>
            </div>
        );
    }
}

export default App;
