import React from 'react';
import { withRouter } from "react-router";
import { LookupIngDisplayType } from '../../lookups/LookupIngDisplayType';
import { LookupIngUnitType } from '../../lookups/LookupIngUnitType';
import { IngredientModel } from '../../models/IngredientModel';
import Text from '../form/Text';
import Number from '../form/Number';
import Textarea from '../form/Textarea';
import Select from '../form/Select';

const NormalIngredientDisplayType = ({ i, item, recipe, ingredients, handlers }) => {
    const ingredient = ingredients[item.ingredientId] ? ingredients[item.ingredientId] : IngredientModel();

    return (
        <div className="d-flex">
            <Number label="Quantity" value={item.quantity} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'quantity')} />
            <div className="form-group">
                <label>Ingredient</label>
                <select className="form-control" value={item.ingredientId} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'ingredientId')}>
                    {Object.keys(ingredients).map(ingKey => {
                        const ingredient = ingredients[ingKey];
                        return (
                            <option key={`ing-${ingredient.id}`} value={ingredient.id}>{ingredient.name}</option>
                        )
                    })}
                </select>
            </div>
            <div className="form-group">
                <Text label="End Description" value={item.endDesc} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'endDesc')} />
            </div>
            <div className="form-group">
                <label>Unit Type</label>
                <select className="form-control" value={item.ingUnitTypeId} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'ingUnitTypeId')}>
                    {ingredient.unitTypes.map((unit, i) => {
                        const key = `ing-${item.id}-unit-${unit.ratio}-${i}`
                        const unitName = LookupIngUnitType.dataLookup(unit.id).description;
                        return (
                            <option key={key} value={unit.id}>{unitName}</option>
                        )
                    })}
                </select>
            </div>
            <div className="form-group">
                <label>Alternate Unit</label>
                <select className="form-control" value={item.alternateUnit} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'alternateUnit')}>
                    <option value={0}>None</option>
                    {ingredient.unitTypes.map((unit, i) => {
                        const key = `ing-${item.id}-alt-${unit.ratio}-${i}`
                        const unitName = LookupIngUnitType.dataLookup(unit.id).description;
                        return (
                            <option key={key} value={unit.id}>{unitName}</option>
                        )
                    })}
                </select>
            </div>
        </div>
    )
}

const Recipe = ({ data, handlers, match }) => {
    if (data === null) return null

    const recipe = data.recipes[match.params.id];
    const { ingredients } = data;
    
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-xl">
                    <h2>{recipe.title}</h2>
                    <p><small>ID: {recipe.id}</small></p>
                    <Text label="Title" value={recipe.title} onChange={handlers.onRecipeFieldChange(recipe.id, 'title')} />
                    <Text label="Reference URL" value={recipe.referenceURL} onChange={handlers.onRecipeFieldChange(recipe.id, 'referenceURL')} />
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
                    <ul className="list-group">
                        {recipe.ings.map((item, i) => {
                            return (
                                <li key={`recipeitem-${i}`} className="list-group-item">
                                    <button type="button" className="btn btn-link text-danger float-right" onClick={handlers.onRemoveRecipeIng(recipe.id, i)}>Remove</button>
                                    <div className="d-flex">
                                        <Select label="Display Type" value={item.ingDisplayTypeId} options={LookupIngDisplayType.dataList()} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'ingDisplayTypeId')} />
                                        {
                                            item.ingDisplayTypeId === LookupIngDisplayType.normal.id ?
                                                <NormalIngredientDisplayType
                                                    i={i}
                                                    item={item}
                                                    recipe={recipe}
                                                    ingredients={ingredients}
                                                    handlers={handlers}
                                                />
                                            :
                                                ''
                                        }
                                        {
                                            item.ingDisplayTypeId === LookupIngDisplayType.heading.id ?
                                                <Text label="Display" value={item.display} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'display')} />
                                            :
                                                ''
                                        }
                                        {
                                            item.ingDisplayTypeId === LookupIngDisplayType.textOnly.id ?
                                                <Text label="Display" value={item.display} onChange={handlers.onRecipeIngFieldChange(recipe.id, i, 'display')} />
                                                :
                                                ''
                                        }
                                    </div>
                                </li>
                            )
                        })}
                    </ul>
                    <br />
                    <button type="button" className="btn btn-primary" onClick={handlers.onAddRecipeIng(recipe.id)}>+</button>
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-xl">
                    <h6>Method</h6>
                    <ol>
                        {recipe.method.map((item, i) => {
                            return (
                                <li key={`method-${i}`}>
                                    <button type="button" className="btn btn-link text-danger float-right" onClick={handlers.onRemoveMethod(recipe.id, i)}>Remove</button>
                                    <Textarea value={item} onChange={handlers.onRecipeMethodChange(recipe.id, i)} />
                                </li>
                            )
                        })}
                    </ol>
                    <button type="button" className="btn btn-primary" onClick={handlers.onAddMethod(recipe.id)}>+</button>
                </div>
            </div>
        </div>
    )
}

export default withRouter(Recipe);