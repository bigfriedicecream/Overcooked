import { LookupIngDisplayType } from '../lookups/LookupIngDisplayType';

export function NormalIngredientTypeModel() {
    return {
        ingDisplayTypeId: LookupIngDisplayType.normal.id,
        quantity: 0,
        ingredientId: 0,
        alternateUnits: [],
        viewAlternateOnly: false
    }
}