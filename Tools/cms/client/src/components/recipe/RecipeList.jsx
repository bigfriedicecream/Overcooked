import React from 'react';
import { Link } from 'react-router-dom';

const RecipeList = ({ data, handlers }) => {
    if (data === null) return null

    const { recipes } = data

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-xl">
                    <ul className="list-group">
                        {Object.keys(recipes).map(key => {
                            const recipe = recipes[key];
                            return (
                                <li key={`recipe-${recipe.id}`} className="list-group-item">
                                    <Link to={`/recipe/edit/${recipe.id}`}>{recipe.title}</Link>
                                    <button type="button" className="btn btn-danger btn-sm float-right" onClick={handlers.onRemoveRecipe(recipe.id)}>Remove</button>
                                </li>
                            )
                        })}
                    </ul>
                    <br />
                    <button type="button" className="btn btn-primary" onClick={handlers.onAddRecipe}>+</button>
                </div>
            </div>
        </div>
    )
}

export default RecipeList