export const LookupIngUnitType = {
    singular: { id: 0, description: "Singular", shortName: " x ", shortNamePlural: " x " },
    grams: { id: 1, description: "Grams", shortName: "g ", shortNamePlural: "g " },
    millilitres: { id: 2, description: "Millilitres", shortName: "ml ", shortNamePlural: "ml " },
    tsp: { id: 3, description: "Tsp", shortName: " tsp ", shortNamePlural: " tsp " },
    tbs: { id: 5, description: "Tbs", shortName: " tbs ", shortNamePlural: " tbs " },
    cups: { id: 7, description: "Cups", shortName: " cup ", shortNamePlural: " cups " },
    bunch: { id: 8, description: "Bunch", shortName: " bunch of ", shortNamePlural: " bunches of " },
    rashers: { id: 9, description: "Rashers", shortName: " rasher of ", shortNamePlural: " rashes of " },
    head: { id: 10, description: "Head", shortName: " head of ", shortNamePlural: " heads of " },
    sprig: { id: 11, description: "Sprig", shortName: " sprig of ", shortNamePlural: " sprigs of " },
    stalk: { id: 12, description: "Stalk", shortName: " stalk of ", shortNamePlural: " stalks of " },
    sheets: { id: 13, description: "Sheets", shortName: " sheet of ", shortNamePlural: " sheets of " },
    slice: { id: 14, description: "Slice", shortName: " slice of ", shortNamePlural: " slices of " },

    dataLookup(id) {
        if (id === LookupIngUnitType.singular.id) { return LookupIngUnitType.singular; }
        if (id === LookupIngUnitType.grams.id) { return LookupIngUnitType.grams; }
        if (id === LookupIngUnitType.millilitres.id) { return LookupIngUnitType.millilitres; }
        if (id === LookupIngUnitType.tsp.id) { return LookupIngUnitType.tsp; }
        if (id === LookupIngUnitType.tbs.id) { return LookupIngUnitType.tbs; }
        if (id === LookupIngUnitType.cups.id) { return LookupIngUnitType.cups; }
        if (id === LookupIngUnitType.bunch.id) { return LookupIngUnitType.bunch; }
        if (id === LookupIngUnitType.rashers.id) { return LookupIngUnitType.rashers; }
        if (id === LookupIngUnitType.head.id) { return LookupIngUnitType.head; }
        if (id === LookupIngUnitType.sprig.id) { return LookupIngUnitType.sprig; }
        if (id === LookupIngUnitType.stalk.id) { return LookupIngUnitType.stalk; }
        if (id === LookupIngUnitType.sheets.id) { return LookupIngUnitType.sheets; }
        if (id === LookupIngUnitType.slice.id) { return LookupIngUnitType.slice; }
        return {}
    },

    dataList() {
        return [
            LookupIngUnitType.singular,
            LookupIngUnitType.grams,
            LookupIngUnitType.millilitres,
            LookupIngUnitType.tsp,
            LookupIngUnitType.tbs,
            LookupIngUnitType.cups,
            LookupIngUnitType.bunch,
            LookupIngUnitType.rashers,
            LookupIngUnitType.head,
            LookupIngUnitType.sprig,
            LookupIngUnitType.stalk,
            LookupIngUnitType.sheets,
            LookupIngUnitType.slice
        ]
    }
}