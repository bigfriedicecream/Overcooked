export const LookupIngType = {
    normal: { id: 0, description: 'Normal' },
    heading: { id: 1, description: 'Heading' },
    textOnly: { id: 2, description: 'Text Only' },

    dataLookup(id) {
        if (id === LookupIngType.normal.id) { return LookupIngType.normal; }
        if (id === LookupIngType.heading.id) { return LookupIngType.heading; }
        if (id === LookupIngType.textOnly.id) { return LookupIngType.textOnly; }
        return {}
    },

    dataList() {
        return [
            LookupIngType.normal,
            LookupIngType.heading,
            LookupIngType.textOnly
        ]
    }
}