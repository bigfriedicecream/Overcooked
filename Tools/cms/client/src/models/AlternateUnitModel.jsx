import { LookupIngUnitType } from '../lookups/LookupIngUnitType';

export function AlternateUnitModel() {
    return {
        unitTypeId: LookupIngUnitType.singular.id,
        multiplier: 1
    }
}