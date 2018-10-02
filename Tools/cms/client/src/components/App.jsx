import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import Root from './Root';
import RecipeList from './recipe/RecipeList';
import IngredientList from './ingredient/IngredientList';
import './App.css';

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
        data.recipes[id][field] = e.target.value
        this.setState({ data });
    }

    render() {
        const handlers = {
            onRecipeFieldChange: this.onRecipeFieldChange
        }

        return (
            <div>
                <button type="button" className="btn btn-primary" onClick={this.onSave}>Save</button>
                <hr />
                <Switch>
                    <Route exact path='/' component={Root} />
                    <Route path='/recipe/list' render={routeProps => <RecipeList {...routeProps} data={this.state.data} handlers={handlers} />} />
                    <Route path='/ingredient/list' component={IngredientList} />
                </Switch>
            </div>
        );
    }
}

export default App;
