import React from 'react';
import { Link } from 'react-router-dom';

const RecipeList = ({ data, handlers }) => {
    if (data === null) return null

    const { recipes } = data

    return (
        <div>
            <ul className="list-group">
                {Object.keys(recipes).map(key => {
                    const recipe = recipes[key];
                    return (
                        <li key={`recipe-${recipe.id}`} className="list-group-item">
                            <Link to={`/recipe/edit/${recipe.id}`}>{recipe.title}</Link>
                        </li>
                    )
                })}
            </ul>
        </div>
    )
}

export default RecipeList