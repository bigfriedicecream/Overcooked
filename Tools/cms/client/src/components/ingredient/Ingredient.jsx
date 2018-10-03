import React from 'react';
import { withRouter } from "react-router";
import { LookupIngUnitType } from '../../lookups/LookupIngUnitType';
import Text from '../form/Text';
import Select from '../form/Select';

const Ingredient = ({ data, handlers, match }) => {
    if (data === null) return null

    const ingredient = data.ingredients[match.params.id];
    
    return (
        <div>
            <Text label="Name" value={ingredient.name} onChange={handlers.onIngredientFieldChange(ingredient.id, 'name')} />
            <Text label="Name Plural" value={ingredient.namePlural} onChange={handlers.onIngredientFieldChange(ingredient.id, 'namePlural')} />
            <Select label="Unit Type" value={ingredient.ingUnitType} onChange={handlers.onIngredientFieldChange(ingredient.id, 'ingUnitType')} options={LookupIngUnitType.dataList()} />
        </div>
    )
}

export default withRouter(Ingredient);