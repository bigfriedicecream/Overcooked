import React from 'react';
import { withRouter } from "react-router";
import { LookupIngUnitType } from '../../lookups/LookupIngUnitType';
import Text from '../form/Text';
import Select from '../form/Select';

const Ingredient = ({ data, handlers, match }) => {
    if (data === null) return null

    const ingredient = data.ingredients[match.params.id];
    
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-xl">
                    <h2>{ingredient.name}</h2>
                    <p><small>ID: {ingredient.id}</small></p>
                    <Text label="Name" value={ingredient.name} onChange={handlers.onIngredientFieldChange(ingredient.id, 'name')} />
                    <Text label="Name Plural" value={ingredient.namePlural} onChange={handlers.onIngredientFieldChange(ingredient.id, 'namePlural')} />
                    <Select label="Unit Type" value={ingredient.ingUnitTypeId} onChange={handlers.onIngredientFieldChange(ingredient.id, 'ingUnitTypeId')} options={LookupIngUnitType.dataList()} />
                </div>
            </div>
        </div>
    )
}

export default withRouter(Ingredient);