import React from 'react';

const RecipeList = ({ data, handlers }) => {
    if (data === null) return null

    const { recipes } = data

    return (
        <div>
            {Object.keys(recipes).map(key => {
                const recipe = recipes[key];
                // console.log(recipe);
                return (
                    <div key={`recipe-${recipe.id}`}>
                        <h2>{recipe.title}</h2>
                        <div className="form-group">
                            <label>Title</label>
                            <input type="text" className="form-control" value={recipe.title} onChange={handlers.onRecipeFieldChange(recipe.id, 'title')} />
                        </div>
                    </div>
                )
            })}
        </div>
    )
}

export default RecipeList