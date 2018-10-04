import { LookupIngUnitType } from '../lookups/LookupIngUnitType';
const uuidv4 = require('uuid/v4');

export function IngredientModel() {
    return {
        id: uuidv4(),
        name: '[name]',
        namePlural: '[name plural]',
        unitType: LookupIngUnitType.singular.id
    }
}