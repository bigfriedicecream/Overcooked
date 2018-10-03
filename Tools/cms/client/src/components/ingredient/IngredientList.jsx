import React from 'react';
import { Link } from 'react-router-dom';

const IngredientList = ({ data, handlers }) => {
    if (data === null) return null

    const { ingredients } = data

    return (
        <div>
            <ul className="list-group">
                {Object.keys(ingredients).map(key => {
                    const ingredient = ingredients[key];
                    return (
                        <li key={`ingredient-${ingredient.id}`} className="list-group-item">
                            <Link to={`/ingredient/edit/${ingredient.id}`}>{ingredient.name}</Link>
                        </li>
                    )
                })}
            </ul>
        </div>
    )
}

export default IngredientList