<template>

    <v-data-table
        :headers="headers"
        :items="selectRoom"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'SelectRoomView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            selectRoom : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/selectRooms'))

            temp.data._embedded.selectRooms.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.selectRoom = temp.data._embedded.selectRooms;
        },
        methods: {
        }
    }
</script>

