<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhProject4App.client.home.title')" id="client-heading">Clients</span>
            <router-link :to="{name: 'ClientCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-client">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhProject4App.client.home.createLabel')">
                    Create a new Client
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && clients && clients.length === 0">
            <span v-text="$t('jhProject4App.client.home.notFound')">No clients found</span>
        </div>
        <div class="table-responsive" v-if="clients && clients.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('jhProject4App.client.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('age')"><span v-text="$t('jhProject4App.client.age')">Age</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'age'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('phoneNumber')"><span v-text="$t('jhProject4App.client.phoneNumber')">Phone Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('email')"><span v-text="$t('jhProject4App.client.email')">Email</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('photo')"><span v-text="$t('jhProject4App.client.photo')">Photo</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="client in clients"
                    :key="client.id">
                    <td>
                        <router-link :to="{name: 'ClientView', params: {clientId: client.id}}">{{client.id}}</router-link>
                    </td>
                    <td>{{client.name}}</td>
                    <td>{{client.age}}</td>
                    <td>{{client.phoneNumber}}</td>
                    <td>{{client.email}}</td>
                    <td>
                        <a v-if="client.photo" v-on:click="openFile(client.photoContentType, client.photo)">
                            <img v-bind:src="'data:' + client.photoContentType + ';base64,' + client.photo" style="max-height: 30px;" alt="client image"/>
                        </a>
                        <span v-if="client.photo">{{client.photoContentType}}, {{byteSize(client.photo)}}</span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ClientView', params: {clientId: client.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ClientEdit', params: {clientId: client.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(client)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="jhProject4App.client.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-client-heading" v-text="$t('jhProject4App.client.delete.question', {'id': removeId})">Are you sure you want to delete this Client?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-client" v-text="$t('entity.action.delete')" v-on:click="removeClient()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./client.component.ts">
</script>
