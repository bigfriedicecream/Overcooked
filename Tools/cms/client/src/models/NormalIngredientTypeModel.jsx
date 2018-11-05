import { LookupIngDisplayType } from '../lookups/LookupIngDisplayType';
import { LookupIngUnitType } from '../lookups/LookupIngUnitType';

export function NormalIngredientTypeModel() {
    return {
        ingDisplayTypeId: LookupIngDisplayType.normal.id,
        quantity: 0,
        ingredientId: 0,
        unitTypeId: LookupIngUnitType.singular.id,
        alternateUnits: [],
        endDesc: ''
    }
}