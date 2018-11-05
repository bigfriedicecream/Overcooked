import React from 'react';
import { Link } from 'react-router-dom';

const IngredientList = ({ data, handlers }) => {
    if (data === null) return null

    const { ingredients } = data
    const sortedIngredients = Object.keys(ingredients).sort((a,b) => {
        const aName = ingredients[a].name.toLowerCase();
        const bName = ingredients[b].name.toLowerCase();
        if (aName < bName) { return -1; }
        if (aName > bName) { return 1; }
        return 0;
    })

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-xl">
                    <ul className="list-group">
                        {sortedIngredients.map(key => {
                            const ingredient = ingredients[key];
                            return (
                                <li key={`ingredient-${ingredient.id}`} className="list-group-item">
                                    <Link to={`/ingredient/edit/${ingredient.id}`}>{ingredient.name}</Link>
                                    <button type="button" className="btn btn-danger btn-sm float-right" onClick={handlers.onRemoveIngredient(ingredient.id)}>Remove</button>
                                </li>
                            )
                        })}
                    </ul>
                    <br />
                    <button type="button" className="btn btn-primary" onClick={handlers.onAddIngredient}>+</button>
                </div>
            </div>
        </div>
    )
}

export default IngredientList