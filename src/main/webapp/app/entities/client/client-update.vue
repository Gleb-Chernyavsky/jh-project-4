<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhProject4App.client.home.createOrEditLabel" v-text="$t('jhProject4App.client.home.createOrEditLabel')">Create or edit a Client</h2>
                <div>
                    <div class="form-group" v-if="client.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="client.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.client.name')" for="client-name">Name</label>
                        <input type="text" class="form-control" name="name" id="client-name"
                            :class="{'valid': !$v.client.name.$invalid, 'invalid': $v.client.name.$invalid }" v-model="$v.client.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.client.age')" for="client-age">Age</label>
                        <input type="number" class="form-control" name="age" id="client-age"
                            :class="{'valid': !$v.client.age.$invalid, 'invalid': $v.client.age.$invalid }" v-model.number="$v.client.age.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.client.phoneNumber')" for="client-phoneNumber">Phone Number</label>
                        <input type="text" class="form-control" name="phoneNumber" id="client-phoneNumber"
                            :class="{'valid': !$v.client.phoneNumber.$invalid, 'invalid': $v.client.phoneNumber.$invalid }" v-model="$v.client.phoneNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.client.email')" for="client-email">Email</label>
                        <input type="text" class="form-control" name="email" id="client-email"
                            :class="{'valid': !$v.client.email.$invalid, 'invalid': $v.client.email.$invalid }" v-model="$v.client.email.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.client.photo')" for="client-photo">Photo</label>
                        <div>
                            <img v-bind:src="'data:' + client.photoContentType + ';base64,' + client.photo" style="max-height: 100px;" v-if="client.photo" alt="client image"/>
                            <div v-if="client.photo" class="form-text text-danger clearfix">
                                <span class="pull-left">{{client.photoContentType}}, {{byteSize(client.photo)}}</span>
                                <button type="button" v-on:click="clearInputImage('photo', 'photoContentType', 'file_photo')" class="btn btn-secondary btn-xs pull-right">
                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                </button>
                            </div>
                            <input type="file" ref="file_photo" id="file_photo" v-on:change="setFileData($event, client, 'photo', true)" accept="image/*" v-text="$t('entity.action.addimage')"/>
                        </div>
                        <input type="hidden" class="form-control" name="photo" id="client-photo"
                            :class="{'valid': !$v.client.photo.$invalid, 'invalid': $v.client.photo.$invalid }" v-model="$v.client.photo.$model" />
                        <input type="hidden" class="form-control" name="photoContentType" id="client-photoContentType"
                            v-model="client.photoContentType" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.client.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./client-update.component.ts">
</script>
