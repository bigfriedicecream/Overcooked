import { LookupIngDisplayType } from '../lookups/LookupIngDisplayType';

export function TextOnlyIngredientTypeModel() {
    return {
        ingDisplayTypeId: LookupIngDisplayType.textOnly.id,
        display: '[display]'
    }
}