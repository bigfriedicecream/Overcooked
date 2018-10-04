import React from 'react';
import { withRouter } from "react-router";
import Text from '../form/Text';
import Number from '../form/Number';
import Textarea from '../form/Textarea';

const Recipe = ({ data, handlers, match }) => {
    if (data === null) return null

    const recipe = data.recipes[match.params.id];
    
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-xl">
                    <h2>{recipe.title}</h2>
                    <p><small>ID: {recipe.id}</small></p>
                    <Text label="Title" value={recipe.title} onChange={handlers.onRecipeFieldChange(recipe.id, 'title')} />
                    <div className="d-flex">
                        <Number label="Serves" value={recipe.serves} onChange={handlers.onRecipeFieldChange(recipe.id, 'serves')} />
                        <Number label="Makes" value={recipe.makes} onChange={handlers.onRecipeFieldChange(recipe.id, 'makes')} />
                        <Number label="Prep Time" value={recipe.prepTime} onChange={handlers.onRecipeFieldChange(recipe.id, 'prepTime')} />
                        <Number label="Cook Time" value={recipe.cookTime} onChange={handlers.onRecipeFieldChange(recipe.id, 'cookTime')} />
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col-xl">
                    <h6>Ingredients</h6>
                </div>
            </div>
            <div className="row">
                <div className="col-xl">
                    <h6>Method</h6>
                    {recipe.method.map((item, i) => {
                        return <Textarea key={`method-${i}`} value={item} onChange={handlers.onRecipeMethodChange(recipe.id, i)} />
                    })}
                </div>
            </div>
        </div>
    )
}

export default withRouter(Recipe);