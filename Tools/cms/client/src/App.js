import React, { Component } from 'react';
import './App.css';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: ''
        }
    }

    componentDidMount() {
        this.callApi()
            .then(res => this.setState({ title: res.title }))
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
            <div>
                <input type="text" value={this.state.title} onChange={this.onTitleChange} />
                <button type="button" onClick={this.onSave}>Save</button>
            </div>
        );
    }
}

export default App;
