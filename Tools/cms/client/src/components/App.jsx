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
            data: ''
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

    onTitleChange = e => {
        this.setState({ title: e.target.value });
    }

    onSave = async() => {
        const output = JSON.stringify(Object.assign({}, this.state));

        const response = await fetch('/recipes/save', {
            method: 'POST',
            body: output,
            headers: { "Content-Type": "application/json" }
        });

        if (response.status !== 200) throw Error(response.statusText);

        return true;
    }

    render() {
        return (
            <Switch>
                <Route exact path='/' component={Root} />
                <Route path='/recipe/list' component={RecipeList} />
                <Route path='/ingredient/list' component={IngredientList} />
            </Switch>
        );
    }
}

export default App;
