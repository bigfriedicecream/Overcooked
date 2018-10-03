import React from 'react';
import { Link } from 'react-router-dom';

const RecipeList = ({ data, handlers }) => {
    if (data === null) return null

    const { recipes } = data

    return (
        <div>
            {Object.keys(recipes).map(key => {
                const recipe = recipes[key];
                return (
                    <Link key={`recipe-${recipe.id}`} to={`/recipe/edit/${recipe.id}`}>{recipe.title}</Link>
                )
            })}
        </div>
    )
}

export default RecipeList