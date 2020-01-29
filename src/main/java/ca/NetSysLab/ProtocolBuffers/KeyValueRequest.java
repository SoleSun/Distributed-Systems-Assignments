// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: KeyValueRequest.proto

package ca.NetSysLab.ProtocolBuffers;

public final class KeyValueRequest {
  private KeyValueRequest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface KVRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:KVRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required uint32 command = 1;</code>
     */
    boolean hasCommand();
    /**
     * <code>required uint32 command = 1;</code>
     */
    int getCommand();

    /**
     * <code>optional bytes key = 2;</code>
     */
    boolean hasKey();
    /**
     * <code>optional bytes key = 2;</code>
     */
    com.google.protobuf.ByteString getKey();

    /**
     * <code>optional bytes value = 3;</code>
     */
    boolean hasValue();
    /**
     * <code>optional bytes value = 3;</code>
     */
    com.google.protobuf.ByteString getValue();

    /**
     * <code>optional int32 version = 4;</code>
     */
    boolean hasVersion();
    /**
     * <code>optional int32 version = 4;</code>
     */
    int getVersion();
  }
  /**
   * Protobuf type {@code KVRequest}
   */
  public static final class KVRequest extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:KVRequest)
      KVRequestOrBuilder {
    // Use KVRequest.newBuilder() to construct.
    private KVRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private KVRequest(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final KVRequest defaultInstance;
    public static KVRequest getDefaultInstance() {
      return defaultInstance;
    }

    public KVRequest getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private KVRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              command_ = input.readUInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              key_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              value_ = input.readBytes();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              version_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ca.NetSysLab.ProtocolBuffers.KeyValueRequest.internal_static_KVRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ca.NetSysLab.ProtocolBuffers.KeyValueRequest.internal_static_KVRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest.class, ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest.Builder.class);
    }

    public static com.google.protobuf.Parser<KVRequest> PARSER =
        new com.google.protobuf.AbstractParser<KVRequest>() {
      public KVRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new KVRequest(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<KVRequest> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int COMMAND_FIELD_NUMBER = 1;
    private int command_;
    /**
     * <code>required uint32 command = 1;</code>
     */
    public boolean hasCommand() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required uint32 command = 1;</code>
     */
    public int getCommand() {
      return command_;
    }

    public static final int KEY_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString key_;
    /**
     * <code>optional bytes key = 2;</code>
     */
    public boolean hasKey() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bytes key = 2;</code>
     */
    public com.google.protobuf.ByteString getKey() {
      return key_;
    }

    public static final int VALUE_FIELD_NUMBER = 3;
    private com.google.protobuf.ByteString value_;
    /**
     * <code>optional bytes value = 3;</code>
     */
    public boolean hasValue() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional bytes value = 3;</code>
     */
    public com.google.protobuf.ByteString getValue() {
      return value_;
    }

    public static final int VERSION_FIELD_NUMBER = 4;
    private int version_;
    /**
     * <code>optional int32 version = 4;</code>
     */
    public boolean hasVersion() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 version = 4;</code>
     */
    public int getVersion() {
      return version_;
    }

    private void initFields() {
      command_ = 0;
      key_ = com.google.protobuf.ByteString.EMPTY;
      value_ = com.google.protobuf.ByteString.EMPTY;
      version_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasCommand()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeUInt32(1, command_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, key_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, value_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, version_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, command_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, key_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, value_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, version_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code KVRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:KVRequest)
        ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ca.NetSysLab.ProtocolBuffers.KeyValueRequest.internal_static_KVRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ca.NetSysLab.ProtocolBuffers.KeyValueRequest.internal_static_KVRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest.class, ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest.Builder.class);
      }

      // Construct using ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        command_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        key_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        value_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        version_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ca.NetSysLab.ProtocolBuffers.KeyValueRequest.internal_static_KVRequest_descriptor;
      }

      public ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest getDefaultInstanceForType() {
        return ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest.getDefaultInstance();
      }

      public ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest build() {
        ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest buildPartial() {
        ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest result = new ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.command_ = command_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.key_ = key_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.value_ = value_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.version_ = version_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest) {
          return mergeFrom((ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest other) {
        if (other == ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest.getDefaultInstance()) return this;
        if (other.hasCommand()) {
          setCommand(other.getCommand());
        }
        if (other.hasKey()) {
          setKey(other.getKey());
        }
        if (other.hasValue()) {
          setValue(other.getValue());
        }
        if (other.hasVersion()) {
          setVersion(other.getVersion());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCommand()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ca.NetSysLab.ProtocolBuffers.KeyValueRequest.KVRequest) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int command_ ;
      /**
       * <code>required uint32 command = 1;</code>
       */
      public boolean hasCommand() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required uint32 command = 1;</code>
       */
      public int getCommand() {
        return command_;
      }
      /**
       * <code>required uint32 command = 1;</code>
       */
      public Builder setCommand(int value) {
        bitField0_ |= 0x00000001;
        command_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 command = 1;</code>
       */
      public Builder clearCommand() {
        bitField0_ = (bitField0_ & ~0x00000001);
        command_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString key_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes key = 2;</code>
       */
      public boolean hasKey() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional bytes key = 2;</code>
       */
      public com.google.protobuf.ByteString getKey() {
        return key_;
      }
      /**
       * <code>optional bytes key = 2;</code>
       */
      public Builder setKey(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        key_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes key = 2;</code>
       */
      public Builder clearKey() {
        bitField0_ = (bitField0_ & ~0x00000002);
        key_ = getDefaultInstance().getKey();
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString value_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes value = 3;</code>
       */
      public boolean hasValue() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional bytes value = 3;</code>
       */
      public com.google.protobuf.ByteString getValue() {
        return value_;
      }
      /**
       * <code>optional bytes value = 3;</code>
       */
      public Builder setValue(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        value_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes value = 3;</code>
       */
      public Builder clearValue() {
        bitField0_ = (bitField0_ & ~0x00000004);
        value_ = getDefaultInstance().getValue();
        onChanged();
        return this;
      }

      private int version_ ;
      /**
       * <code>optional int32 version = 4;</code>
       */
      public boolean hasVersion() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 version = 4;</code>
       */
      public int getVersion() {
        return version_;
      }
      /**
       * <code>optional int32 version = 4;</code>
       */
      public Builder setVersion(int value) {
        bitField0_ |= 0x00000008;
        version_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 version = 4;</code>
       */
      public Builder clearVersion() {
        bitField0_ = (bitField0_ & ~0x00000008);
        version_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:KVRequest)
    }

    static {
      defaultInstance = new KVRequest(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:KVRequest)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_KVRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_KVRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025KeyValueRequest.proto\"I\n\tKVRequest\022\017\n\007" +
      "command\030\001 \002(\r\022\013\n\003key\030\002 \001(\014\022\r\n\005value\030\003 \001(" +
      "\014\022\017\n\007version\030\004 \001(\005B/\n\034ca.NetSysLab.Proto" +
      "colBuffersB\017KeyValueRequest"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_KVRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_KVRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_KVRequest_descriptor,
        new java.lang.String[] { "Command", "Key", "Value", "Version", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}